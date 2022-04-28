import sqlite3, json
from flask import g, Flask
from requests import request


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

#recipes(name text, ingredients text, servings int, directions text)
@app.route('/recipes')
def get_recipes():
    recipes = []
    for recipe in query_db('select * from recipes'):
        recipes.append(recipe)
    return json.dumps(recipes)

#needs fixing ...
@app.route('/add')
def add_product():

    product_id = request.form['ProductId']
    product_name = request.form['ProductName']
    price = request.form['Price']
    vendor_id = request.form['VendorId']
    vendor_name = request.form['VendorName']

    query_db("insert into products(product_id,product_name,price,vendor_name,vendor_id) values ({pid},{pname},{price},{vname},{vid})".format(pid=product_id,pname=product_name,price=price,vname=vendor_name,vid=vendor_id))

