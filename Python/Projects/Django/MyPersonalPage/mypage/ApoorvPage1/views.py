from django.shortcuts import render


# from django.http import HttpResponse

def index(request):
    return render(request, 'ApoorvPage1/home.html')
    # return HttpResponse("<h2>Hi! its my page</h2>")


def contact(request):
    return render(request, 'ApoorvPage1/basic.html', {'content': ['I can be reached at', 'apoorv.semwal20@gmail.com']})
