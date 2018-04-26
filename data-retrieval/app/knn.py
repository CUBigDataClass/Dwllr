from sklearn.neighbors import NearestNeighbors
from cassandra.cluster import Cluster

session = Cluster(["localhost"]).connect("dwllr")
rv = session.execute("SELECT * from normalize")
cols = rv.current_rows[0]._fields
zip_to_index = {}
index_to_zip = {}
data = []
i = 0
for zip_code, *col_data in map(list, rv):
    if None not in col_data:    
        zip_to_index[zip_code] = i
        index_to_zip[i] = zip_code
        data.append([float(i) for i in col_data])
        i += 1
        
nbrs = NearestNeighbors().fit(data)

def get_knn(zip_code, k=5):
    indicies, = nbrs.kneighbors([data[zip_to_index[zip_code]]], n_neighbors=k, return_distance=False)
    return [index_to_zip[i] for i in indicies]