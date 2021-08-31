import sys

import pandas as pd
import os
import bs4
import re
import requests
from sklearn.feature_extraction.text import CountVectorizer
import psycopg2
from psycopg2 import Error

def file_len(fname):
    with open(fname) as f:
        for i, l in enumerate(f):
            pass
    return i + 1

#helper function used to sort files in folder
def sorted_alphanumeric(data):
    convert = lambda text: int(text) if text.isdigit() else text.lower()
    alphanum_key = lambda key: [convert(c) for c in re.split('([0-9]+)', key)] 
    return sorted(data, key = alphanum_key)

print(os.getcwd())
#folder where all the html files are
currdir = "/Users/rai7x/CS/TM/Database/html/"

docs = [] #array to hold the text of the outputted text files
chosen_elements = {'td'} #html elements we care about
folderpath = sorted_alphanumeric(os.listdir(currdir)) #sorting the files in the folder
# uncomment to compile html into txt
i = 1
j = 1
name = "c"

for filename in folderpath: #for each file in folder
    # uncomment to compile html into txt
    if i > 10: i = 1
    if j <= 10: name = "c"
    elif (j > 10 and j <= 20): name = "p"
    elif j > 20: name = "r"
    filedir = currdir + filename
    outfile = "/Users/rai7x/CS/TM/Database/out/" + filename + "out.txt"

    with open(filedir, 'r', encoding="utf-8", errors='ignore') as f: #parse through the html file
        contents = f.read()
        soup = bs4.BeautifulSoup(contents, 'html.parser')

        with open(outfile, 'w', encoding="utf-8", errors='ignore') as fout:
            for element in soup.find_all(text=True):
                if element.parent.name in chosen_elements: #if the element is one we care about, write it to file
                    print(element, file=fout)

        # uncomment to compile html into txt
        with open(name + str(i) + ".txt", 'w', encoding="utf-8", errors='ignore') as fout:
            for element in soup.find_all(text=True):
                if element.parent.name in chosen_elements: #if the element is one we care about, write it to file
                    print(element, file=fout)

        newfile = open(outfile, "r", encoding="utf-8", errors='ignore') #open the file, then store content into docs array
        docs.append(newfile.read())
    # uncomment to compile html into txt    
    i+=1
    j+=1

# write to an excel file
# vec = CountVectorizer()
# X = vec.fit_transform(docs)
# df = pd.DataFrame(X.toarray(), columns=vec.get_feature_names())
# df.to_excel(r'C:\Users\andyt\Desktop\export_dataframe.xlsx', index = False)

# print(df)

txtfile = open(outfile)
lines = txtfile.readlines()
with open('/Users/rai7x/CS/TM/Database/src/games.csv', 'w') as f:
    sys.stdout = f # Change the standard output to the file we created.
    print(file_len("/Users/rai7x/CS/TM/Database/src/games.txt")+1, end=',')
    print(lines[16][0:2].upper(), end=',')
    print(lines[20][:lines[20].index("\n")], end=',')
    print(lines[0][lines[0].index("(")+1:lines[0].index("\n")], end=',')
    print(lines[17][0:2].upper(), end=',')
    print(lines[21][:lines[21].index("\n")], end=',')
    print(lines[2][lines[2].index("(")+1:lines[2].index("\n")], end=',')
    print(lines[18][0:2].upper(), end=',')
    print(lines[22][:lines[22].index("\n")], end=',')
    print(lines[4][lines[4].index("(")+1:lines[4].index("\n")], end=',')
    print(lines[19][0:2].upper(), end=',')
    print(lines[23][:lines[23].index("\n")], end=',')
    print(lines[6][lines[6].index("(")+1:lines[6].index("\n")], end='')

# Change the standard output back to default.
sys.stdout = sys.__stdout__

try:
    # Connect to an existing database
    connection = psycopg2.connect(user="rai7x",
                                  password="",
                                  host="127.0.0.1",
                                  port="5432",
                                  database="terra_mystica")

    # Create a cursor to perform database operations
    cursor = connection.cursor()
    # Print PostgreSQL details
    print("PostgreSQL server information")
    print(connection.get_dsn_parameters(), "\n")
    # Executing a SQL query
    cursor.execute("copy games from '/Users/rai7x/CS/TM/Database/src/games.csv' delimiter ',';")
    cursor.execute("copy games to '/Users/rai7x/CS/TM/Database/src/games.txt' delimiter E'\t';")
    connection.commit()
    # Fetch result
    record = cursor.fetchmany()
    print("You are connected to - ", record, "\n")

except (Exception, Error) as error:
    print("Error while connecting to PostgreSQL", error)
finally:
    if (connection):
        cursor.close()
        connection.close()
        print("PostgreSQL connection is closed")