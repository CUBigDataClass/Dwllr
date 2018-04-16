from flask import Flask, jsonify, request
from cassandra.cluster import Cluster
from cassandra.query import SimpleStatement
from flask_cors import CORS

import os

app = Flask(__name__)
CORS(app)

if os.environ.get("DWLLR_LOCAL", "false") == "true":
    cluster = Cluster(["localhost"])
else:
    cluster = Cluster(["cassandra"])

session = cluster.connect("dwllr")  

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
        rv = session.execute(
            SimpleStatement("SELECT zip, population FROM stats WHERE zip=%s"),
            (str(city), )
        )
    except Exception as e:
        return error(repr(e))

    if len(rv.current_rows) == 0:
        return error("zip code not found")

    return jsonify({
        "data": {
            "stats": {
                "population": rv.current_rows[0].population
            }
        }
    })

if __name__ == "__main__":
    app.run(debug=True, host='0.0.0.0', port=3000)