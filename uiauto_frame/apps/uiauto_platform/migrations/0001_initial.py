# Generated by Django 2.1.7 on 2019-07-07 03:02

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        ('project', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='AppCase',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('caseName', models.CharField(max_length=20, verbose_name='用例名称')),
                ('testResult', models.BooleanField(verbose_name='测试结果')),
                ('tester', models.CharField(blank=True, max_length=20, null=True, verbose_name='测试人')),
                ('createTime', models.DateTimeField(auto_now=True, verbose_name='创建时间')),
                ('product', models.ForeignKey(null=True, on_delete=True, to='project.Product')),
            ],
            options={
                'verbose_name': '测试用例',
                'verbose_name_plural': '测试用例',
                'db_table': 'app_test',
            },
        ),
        migrations.CreateModel(
            name='CaseStep',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('testStep', models.CharField(max_length=10, verbose_name='测试步骤')),
                ('action', models.CharField(max_length=10, verbose_name='执行的动作')),
                ('findMethod', models.CharField(max_length=10, verbose_name='定位方法')),
                ('element', models.CharField(max_length=10, verbose_name='控件值')),
                ('execMethod', models.CharField(max_length=10, verbose_name='操作方法')),
                ('testData', models.CharField(blank=True, max_length=100, null=True, verbose_name='测试数据')),
                ('actual', models.BooleanField(verbose_name='实际结果')),
                ('expect', models.CharField(blank=True, max_length=100, null=True, verbose_name='预期结果')),
                ('createTime', models.DateTimeField(auto_now=True, verbose_name='创建时间')),
                ('case', models.ForeignKey(null=True, on_delete=True, to='uiauto_platform.AppCase')),
            ],
            options={
                'verbose_name': '用例步骤',
                'verbose_name_plural': '用例步骤',
                'db_table': 'case_step',
            },
        ),
    ]
