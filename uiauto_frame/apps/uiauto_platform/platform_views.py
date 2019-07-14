
from django.contrib.auth.decorators import login_required
from django.shortcuts import render, redirect
from django.contrib import auth
from django.http import HttpResponseRedirect, Http404

from apps.project.models import Product
from apps.uiauto_platform.models import AppCase, CaseStep


@login_required
def index(request):
    user = request.session.get('user')
    if request.user.is_authenticated:
        return render(request, 'base.html', {'user': user})


def login(request):
    requestData = request.POST
    # print(request.method, '-----------------------')
    if requestData:
        userName = requestData.get('userName')
        passwd = requestData.get('passwd')
        user = auth.authenticate(username=userName, password=passwd)
        if user is not None:
            auth.login(request, user)
            request.session['user'] = userName
            response = HttpResponseRedirect('/index/')
            return response
    return render(request, 'login.html')


@login_required
def stepManage(request):
    user = request.session.get('user')
    if request.user.is_authenticated:
        caseList = AppCase.objects.all()

        return render(request, 'caseManage.html', {'user': user, 'case_list': caseList})


@login_required
def logout(request):
    auth.logout(request)
    return redirect('/login/')


@login_required
def caseDetailView(request):
    caseId = request.GET.get('id')
    try:
        stepList = CaseStep.objects.filter(case_id=caseId)
    except AppCase.DoesNotExist:
        raise Http404('id是：%s的用例不存在')
    return render(
        request,
        'caseDetail.html',
        context={'steps': stepList, }
    )


@login_required
def addCase(request):
    productes = []
    # 返回的是list <QuerySet [{'productName': 'klook'}, {'productName': '采贝'}, {'productName': '卡莫'}]>
    productList = Product.objects.values('productName')
    for i in productList:
        productes.append(i.get('productName'))
    return render(
        request,
        'add_case.html',
        context={'productes': productes}
    )
