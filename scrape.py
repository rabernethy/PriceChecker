
# Scrape.py written by Russell Abernethy
from distutils.command.config import LANG_EXT
from bs4 import BeautifulSoup
import requests

fg_urls = ['https://www.thefreshgrocer.com/sm/pickup/rsid/2000/categories/produce/fresh-vegetables-id-520538','https://www.thefreshgrocer.com/sm/pickup/rsid/2000/categories/produce/fresh-fruit-id-520537','https://www.thefreshgrocer.com/sm/pickup/rsid/2000/categories/bread-&-bakery/packaged-bread-id-520565','https://www.thefreshgrocer.com/sm/pickup/rsid/2000/categories/dairy/packaged-cheese-id-520599','https://www.thefreshgrocer.com/sm/pickup/rsid/2000/categories/dairy/yogurt-id-520598','https://www.thefreshgrocer.com/sm/pickup/rsid/2000/categories/dairy/milk-id-520592','https://www.thefreshgrocer.com/sm/pickup/rsid/2000/categories/dairy/butter-&-margarine-id-520589','https://www.thefreshgrocer.com/sm/pickup/rsid/2000/categories/dairy/eggs-&-egg-substitutes-id-520591','https://www.thefreshgrocer.com/sm/pickup/rsid/2000/categories/dairy/cottage-cheese-cream-cheese-&-spreads-id-520600','https://www.thefreshgrocer.com/sm/pickup/rsid/2000/categories/dairy/sour-cream-id-520594','https://www.thefreshgrocer.com/sm/pickup/rsid/2000/categories/meat/chicken-id-519882','https://www.thefreshgrocer.com/sm/pickup/rsid/2000/categories/meat/beef-id-519883','https://www.thefreshgrocer.com/sm/pickup/rsid/2000/categories/meat/pork-id-519887','https://www.thefreshgrocer.com/sm/pickup/rsid/2000/categories/meat/turkey-id-519886']

total_items = 0
for url in fg_urls:

    page = requests.get(url)
    soup = BeautifulSoup(page.content, "html.parser")
    products = soup.find_all('div', class_=lambda x: x and x.startswith('ColListing--'))
    
    for munchie in products:

        total_items += 1
        name = munchie.find('span', class_= lambda x: x and x.startswith('ProductCardTitle--')).get_text()
        name = name[ :name.find(",")]
        price = munchie.find('span', class_=lambda x: x and x.startswith('ProductCardPrice--')).get_text()
        print("Item: {name}, Cost: {price}".format(name=name, price=price))
        
print("Total Items Scraped: {}".format(total_items))