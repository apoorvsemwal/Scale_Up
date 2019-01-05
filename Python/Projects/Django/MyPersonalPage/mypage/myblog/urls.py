from django.conf.urls import url
from django.views.generic import ListView, DetailView
from myblog.models import Post

urlpatterns = [
    url(r'^$', ListView.as_view(queryset=Post.objects.all().order_by("-date")[:25],template_name="myblog/blog.html")),
    url(r'^(?P<pk>\d+)$', DetailView.as_view(context_object_name="post",
                                             model=Post,
                                             template_name="myblog/post.html"))
]
