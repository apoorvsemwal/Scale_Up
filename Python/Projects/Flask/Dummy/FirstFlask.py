from flask import Flask, request 
import pandas as pd

data_frame = pd.read_csv("physician_rx.csv")
app = Flask(__name__)

@app.route("/")
def hello():
    return "Hello World!"

@app.route("/preview")
def preview():
    #return data_frame.head().to_json()
    #return data_frame.head().to_json(orient="records") #To Orient the records
    #row_count = request.args.get("rows", type=int, default=5)
    #preview_df = data_frame[0:row_count]

    start    = request.args.get("start",type=int,default=0)
    end      = request.args.get("end",type=int,default=0)

    #Geneartors - Instead of keeping in memory Execute data as and when required
    col_list = list(map(int, request.args.get("columns",type=str,default="0").split(",")))
    #col_list = list(map(lambda x: int(x), request.args.get("columns",type=str,default="0").split(",")))

    preview_df = data_frame[start:end]
    preview_df = preview_df.iloc[start:end,col_list]
    return preview_df.to_html() #To render as HTML Pandas has that function to convert it to HTML table

@app.route("/search")
def search():
    column   = request.args.get("name",type=str,default="0")
    operator = request.args.get("operator",type=str,default="0")
    value    = request.args.get("value",type=float)

    if operator == "=":
        preview_df = data_frame[data_frame[column] == value]
    elif operator == ">":
        preview_df = data_frame[data_frame[column] > value]
    elif operator == "<":
        preview_df = data_frame[data_frame[column] < value]

    return preview_df.to_html() #To render as HTML Pandas has that function to convert it to HTML table


@app.route("/column/<column_name>")   #dyanamic path
def coloumn_preview(column_name):
    #return data_frame[[column_name]].to_html()  #Two brackets to make it a data frame as toHTML works on a data frame
    return data_frame[column_name].to_frame().to_html() #Converting a series to a data frame


if __name__ == "__main__":
    app.run()
