from flask import Flask, jsonify, request
from cassandra.cluster import Cluster
from cassandra.query import SimpleStatement
from uwsgidecorators import postfork

import time

app = Flask(__name__)

cluster = None
session = None

@postfork
def connect():
    global session, prepared
    cluster = Cluster(["cassandra"])
    session = cluster.connect("dwllr")


@app.route("/api/search", methods=["GET"])
def search():

    city = request.args.get("city", -1)
    if city == -1:
        return jsonify({
            "error": {
                "message": "missing city zip codes"
            }
        })

    try:
        print(city)
        rv = session.execute(
            SimpleStatement("SELECT zip, population FROM stats WHERE zip=%s"),
            (str(city), )
        )
    except Exception as e:
        return jsonify({
            "error": {
                "message": repr(e)
            }
        })

    if len(rv.current_rows) == 0:
        return jsonify({
            "error": {
                "message": "zip code not found"
            }
        })
    return jsonify({
        "data": {
            "stats": {
                "population": rv.current_rows[0].population
            }
        }
    })

if __name__ == "__main__":
    app.run(debug=True)