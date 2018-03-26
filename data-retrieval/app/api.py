from flask import Flask, jsonify, request
from cassandra.cluster import Cluster
from cassandra.query import SimpleStatement

app = Flask(__name__)
cluster = Cluster(["127.0.0.1"])
session = cluster.connect("dwllr")

@app.route("/api/search", methods=["GET"])
def search():
    if "city" not in request.form:
        return jsonify({
            "error": {
                "message": "missing city zip code"
            }
        })

    try:
        rv = session.execute(
            SimpleStatement("SELECT zip, population FROM stats WHERE zip=%s"),
            (int(request.form["city"]),)
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