from django.contrib import admin

from apps.uiauto_platform.models import AppCase
from apps.project.models import Product


'''
显示关联项目下的用例
'''


class AppCaseAdminInline(admin.TabularInline):  # TabularInline 水平布局 或继承 StackedInline 垂直布局
    list_display = ['caseName', 'testResult', 'createTime', 'id']
    model = AppCase


@admin.register(Product)
class ProductAdmin(admin.ModelAdmin):
    list_display = ['productName', 'productDesc', 'createTime', 'id']
    inlines = [AppCaseAdminInline]
    list_filter = ('productName',)
