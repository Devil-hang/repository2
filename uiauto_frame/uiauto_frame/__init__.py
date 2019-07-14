import os
import pymysql

pymysql.install_as_MySQLdb()
os.environ['DJANGO_SETTINGS_MODULE'] = 'uiauto_frame.settings'
