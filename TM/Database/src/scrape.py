import bs4
import requests

url = "/Users/rai7x/CS/TM/Database/html/terramystica.html"
chosen_elements = {'h1','h2','h3','h4','h5','h6','p','ul','li'}

html = requests.get(url).text
soup = bs4.BeautifulSoup(html, 'html.parser')

with open('out.txt', 'w', encoding="utf-8") as f:
    for element in soup.find_all(text=True):
        if element.parent.name in chosen_elements:
            print(element, file=f)