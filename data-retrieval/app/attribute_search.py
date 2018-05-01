import pandas as pd
from functools import lru_cache

class AttributeSearch:
    def __init__(self, session):
        rv = session.execute("SELECT * from stats")
        self.cols = rv.current_rows[0]._fields
        data = []
        for zip_code, *col_data in map(list, rv):
            if None not in col_data:
                data.append([zip_code] + [float(i) for i in col_data])
            
        self.df = pd.DataFrame(data, columns=self.cols).set_index("zip")
        self.cache = {}
        
    # @lru_cache()
    def search_by_attributes(self, attrs):
        k = tuple(attrs.items())
        if k in self.cache:
            return self.cache[k] 
        self.cache[k] = self.df.apply(lambda row: sum((row[k]-v)**2 for k, v in attrs.items()), axis=1).sort_values()
        return self.cache[k]
        
