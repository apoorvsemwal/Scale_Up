from django.urls import path
from . import views

urlpatterns = [
    path('',views.index, name='index'),
    path('blog',views.preview, name='blog'),
    path('new',views.new, name='new'),
    path('blog/Name',views.names, name='names'),
]