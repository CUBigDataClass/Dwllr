from sklearn.neighbors import NearestNeighbors
from cassandra.cluster import Cluster

class KNN:
    def __init__(self, session):
        rv = session.execute("SELECT * from normalize")
        cols = rv.current_rows[0]._fields
        self.zip_to_index = {}
        self.index_to_zip = {}
        self.data = []
        i = 0
        for zip_code, *col_data in map(list, rv):
            if None not in col_data:    
                self.zip_to_index[zip_code] = i
                self.index_to_zip[i] = zip_code
                self.data.append([float(i) for i in col_data])
                i += 1
                
        self.nbrs = NearestNeighbors().fit(self.data)

    def get_knn(self, zip_code, k=5):
        indicies, = self.nbrs.kneighbors([self.data[self.zip_to_index[zip_code]]], n_neighbors=k+1, return_distance=False)
        return [self.index_to_zip[i] for i in indicies[1:]]