from django.urls import path
from apps.uiauto_platform import platform_views
urlpatterns = [
    path('', platform_views.login, name='login'),
    path('login/', platform_views.login, name='login'),
    path('accounts/login/',  platform_views.login, name='login'),
    path('logout/', platform_views.logout, name='logout'),
    path('index/', platform_views.index, name='index'),
    path('stepManage/', platform_views.stepManage, name='stepManage'),
    # path('caseDteail/<int:caseId>', platform_views.caseDetailView, name='caseDetail'),
    path('caseDetail/', platform_views.caseDetailView, name='caseDetail'),
    path('add_case/', platform_views.addCase, name='add_case'),
]