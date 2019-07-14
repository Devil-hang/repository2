from django.db import models


class Product(models.Model):
    productName = models.CharField(verbose_name='商品名称', max_length=20)
    productDesc = models.CharField(verbose_name='商品描述', max_length=225)
    createTime = models.DateTimeField(verbose_name='创建时间', auto_now=True)

    class Meta:
        db_table = 'productes'
        verbose_name = '商品管理'
        verbose_name_plural = verbose_name

    def __str__(self):
        return self.productName
