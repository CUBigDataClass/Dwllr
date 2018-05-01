from flask import Flask, jsonify, request
from cassandra.cluster import Cluster
from cassandra.query import SimpleStatement
from cassandra.query import dict_factory
from flask_cors import CORS

import os

from knn import KNN
from attribute_search import AttributeSearch

app = Flask(__name__)
CORS(app)

if os.environ.get("DWLLR_LOCAL", "false") == "true":
    cluster = Cluster(["localhost"])
else:
    cluster = Cluster(["cassandra"])

session = cluster.connect("dwllr")

#load mappings
with open("mappings.txt", "r") as infile:
    mappings = dict(line.split() for line in infile)

#create knn model
model = KNN(session)

ats = AttributeSearch(session)

#output rows are dicts  
session.row_factory = dict_factory

def error(msg):
    return jsonify({
        "error": {
            "message": msg
        }
    })  

@app.route("/api/search", methods=["GET"])
def search():
    if "city" in request.args:
        return search_by_city(request.args["city"])
    attrs = {k:float(v) for k,v in request.args.items() if k in ats.cols}
    if len(attrs) == 0:
        return error("empty attributes")
    return search_by_attribute(attrs)

def search_by_attribute(attrs, n=100):
    res = ats.search_by_attribute(attrs)
    return jsonify({
        "cities": [zipcode for zipcode in res.index[:n]]
    })

def search_by_city(city):
    try:
        print(city)
        s = "SELECT zip," + ",".join(mappings) + " FROM stats WHERE zip=%s"
        rv = session.execute(
            SimpleStatement(s),
            (str(city), )
        )
    except Exception as e:
        return error(repr(e))

    if len(rv.current_rows) == 0:
        return error("zip code not found")

    row = rv.current_rows[0]
    return jsonify({
        "data": {
            "stats": {
                field_name: row[column_name.lower()] 
                for column_name, field_name in mappings.items()
            },
            "similarCities": model.get_knn(city)
        }
    })


if __name__ == "__main__":
    app.run(debug=True, host='0.0.0.0', port=3000)