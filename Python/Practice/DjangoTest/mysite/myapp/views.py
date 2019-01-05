from django.http import HttpResponse
from .models import Post
from django.shortcuts import render
from .forms import PostForm


# Create your views here.
def index(request):
    return HttpResponse("Hello world")


#Read models from DB and render Model in Template
def preview(request):
    posts = Post.objects.all()
    return render(request, "myapp/blog.html", {'posts':posts})

#Read models from DB and render Model in Template
def names(request):
    posts = Post.objects.all()
    return render(request, "myapp/blog.html", {'posts':posts})



def new(request):
    if request.method == "POST":
        form = PostForm(request.POST)
        form.save()
        return HttpResponse("Thanks!!")

    form = PostForm()
    return render(request, "myapp/new.html", {'form':form})