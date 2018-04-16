from flask import Flask, jsonify, request
from cassandra.cluster import Cluster
from cassandra.query import SimpleStatement
from cassandra.query import dict_factory
from flask_cors import CORS

import os

app = Flask(__name__)
CORS(app)

if os.environ.get("DWLLR_LOCAL", "false") == "true":
    cluster = Cluster(["localhost"])
else:
    cluster = Cluster(["cassandra"])

session = cluster.connect("dwllr")

#output rows are dicts  
session.row_factory = dict_factory

#load mappings
with open("mappings.txt", "r") as infile:
    mappings = dict(map(lambda x: x.split(), infile))

def error(msg):
    return jsonify({
        "error": {
            "message": msg
        }
    })  

@app.route("/api/search", methods=["GET"])
def search():
    city = request.args.get("city", None)
    if not city:
        return error("missing city zip codes")

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
                field_name: row[column_name] 
                for column_name, field_name in mappings.items()
            }
        }
    })

if __name__ == "__main__":
    app.run(debug=True, host='0.0.0.0', port=3000)