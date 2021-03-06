# Generated by Django 2.1.7 on 2019-07-07 03:02

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Product',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('productName', models.CharField(max_length=20, verbose_name='商品名称')),
                ('productDesc', models.CharField(max_length=225, verbose_name='商品描述')),
                ('createTime', models.DateTimeField(auto_now=True, verbose_name='创建时间')),
            ],
            options={
                'verbose_name': '商品管理',
                'verbose_name_plural': '商品管理',
                'db_table': 'productes',
            },
        ),
    ]
