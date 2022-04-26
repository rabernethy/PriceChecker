
import sqlite3, json
from flask import g, Flask


app = Flask(__name__)

DATABASE = './mydb'

def get_db():
    db = getattr(g, '_database', None)
    if db is None:
        db = g._database = sqlite3.connect(DATABASE)
    return db

def query_db(query, args=(), one=False):
    cur = get_db().execute(query, args)
    rv = cur.fetchall()
    cur.close()
    return (rv[0] if rv else None) if one else rv

@app.teardown_appcontext
def close_connection(exception):
    db = getattr(g, '_database', None)
    if db is not None:
        db.close()


@app.route("/vendors")
def get_vendors():
    vendors = []
    for vendor in query_db("select * from vendors"):
        vendors.append(vendor)
    return vendors

@app.route('/products')
def get_products():
    products = []
    for product in query_db("select * from products"):
        products.append(product)
    return json.dumps(products)


