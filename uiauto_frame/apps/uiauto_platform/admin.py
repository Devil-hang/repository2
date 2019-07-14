from django.contrib import admin

from apps.uiauto_platform.models import AppCase, CaseStep


class CaseStepAdmin(admin.TabularInline):
    list_display = ['testStep', 'action', 'findMethod', 'element', 'execMethod', 'testData', 'actual', 'expect', 'createTime', 'case', 'id']
    model = CaseStep
    extra = 1


class AppCaseAdmin(admin.ModelAdmin):
    list_display = ['caseName', 'testResult', 'tester', 'createTime', 'id']
    inlines = [CaseStepAdmin]


admin.site.register(AppCase, AppCaseAdmin)

