from django.db import models

from apps.project.models import Product


class AppCase(models.Model):
    product = models.ForeignKey(Product, on_delete=True, null=True)
    caseName = models.CharField(verbose_name='用例名称', max_length=20)
    testResult = models.BooleanField(verbose_name='测试结果')
    tester = models.CharField(verbose_name='测试人', max_length=20, null=True, blank=True)
    createTime = models.DateTimeField(verbose_name='创建时间', auto_now=True)

    class Meta:
        db_table = 'app_test'
        verbose_name = '测试用例'
        verbose_name_plural = verbose_name

    def __str__(self):
        return self.caseName


class CaseStep(models.Model):
    case = models.ForeignKey(AppCase, on_delete=True, null=True)
    testStep = models.CharField(verbose_name='测试步骤', max_length=10)
    action = models.CharField(verbose_name='执行的动作', max_length=10)
    findMethod = models.CharField(verbose_name='定位方法', max_length=10)
    element = models.CharField(verbose_name='控件值', max_length=200)
    execMethod = models.CharField(verbose_name='操作方法', max_length=10)
    testData = models.CharField(verbose_name='测试数据', max_length=100, null=True, blank=True)
    actual = models.BooleanField(verbose_name='实际结果')
    expect = models.CharField(verbose_name='预期结果', max_length=100, null=True, blank=True)
    createTime = models.DateTimeField(verbose_name='创建时间', auto_now=True)

    class Meta:
        db_table = 'case_step'
        verbose_name = '用例步骤'
        verbose_name_plural = verbose_name

    def __str__(self):
        return self.testStep
