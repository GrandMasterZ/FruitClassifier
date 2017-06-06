from bs4 import BeautifulSoup
from PIL import Image
import requests
from io import BytesIO
import re
from urllib.request import urlopen, Request
import os
import json

def get_soup(url,header):
    return BeautifulSoup(urlopen(Request(url,headers=header)),'html.parser')


fruits =  ["banana", "apple", "grapefruit", "kiwi", "lemon", "orange", "pear"] # you can change the query for the image  here
for fruit in fruits:

    url="https://www.google.co.in/search?q="+fruit+"&source=lnms&tbm=isch"
    print(url)
    #add the directory for your image here
    DIR="Pictures"
    header={'User-Agent':"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36"
    }
    soup = get_soup(url,header)


    ActualImages=[]# contains the link for Large original images, type of  image
    for a in soup.find_all("div",{"class":"rg_meta"}):
        link , Type =json.loads(a.text)["ou"]  ,json.loads(a.text)["ity"]
        ActualImages.append((link,Type))

    print("there are total " + str(len(ActualImages)) + " images")

    if not os.path.exists(DIR):
                os.mkdir(DIR)
    DIR = os.path.join(DIR, fruit.split()[0])

    if not os.path.exists(DIR):
                os.mkdir(DIR)

    if not os.path.exists("C:/Users/Mantas/Desktop/Pictures/" + fruit):
        os.makedirs("C:/Users/Mantas/Desktop/Pictures/" + fruit)
    ###print images
    counter = 0
    for i , (img , Type) in enumerate( ActualImages):
        try:
            req = Request(img, headers={'User-Agent' : header})
            response = requests.get(img)
            img = Image.open(BytesIO(response.content))
            if img.format == "JPEG":
                img.save("C:/Users/Mantas/Desktop/Pictures/" + fruit + "/" + fruit + str(counter) + "." + img.format, "JPEG")
            counter = counter + 1
        except Exception as e:
            print(e)