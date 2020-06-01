from tkinter import *
import tkinter.messagebox
import sys
import you_get
import re
import requests
import os

def get_url(page, keyword):
    url = 'https://search.bilibili.com/all?keyword=' + keyword + '&page=' + str(page)
    header = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36'}
    text = requests.get(url=url, headers=header).text
    url = re.findall(r'<div class="headline clearfix"><span class="type avid">(.*?)</span><span class="type hide">(.*?)</span><a title="(.*?)"', text)
    return url

class Tk_class:

    page = 1
    result = None
    part = 0
    root = None
    keyword = None
    fileadd = None
    v0 = None
    v1 = None
    v2 = None
    v3 = None
    v4 = None
    v5 = None
    v6 = None
    v7 = None
    v8 = None
    v9 = None

    def __init__(self, root_master, fileadd, keyword):
        self.root = tkinter.Frame(root_master)
        self.root.pack()
        self.keyword = keyword
        self.fileadd = fileadd
        self.result = get_url(self.page, self.keyword)

        self.v0 = StringVar()
        self.v1 = StringVar()
        self.v2 = StringVar()
        self.v3 = StringVar()
        self.v4 = StringVar()
        self.v5 = StringVar()
        self.v6 = StringVar()
        self.v7 = StringVar()
        self.v8 = StringVar()
        self.v9 = StringVar()

        Entry(self.root, width=70, textvariable=self.v0, state='readonly').grid(row=0, column=0)
        Button(self.root, text='下载', width=10, command=lambda: self.load(self.v0.get())).grid(row=0, column=1,
                                                                                                  sticky=W, padx=10,
                                                                                                  pady=5)

        Entry(self.root, width=70, textvariable=self.v1, state='readonly').grid(row=1, column=0)
        Button(self.root, text='下载', width=10, command=lambda: self.load(self.v1.get())).grid(row=1, column=1,
                                                                                                  sticky=W, padx=10,
                                                                                                  pady=5)

        Entry(self.root, width=70, textvariable=self.v2, state='readonly').grid(row=2, column=0)
        Button(self.root, text='下载', width=10, command=lambda: self.load(self.v2.get())).grid(row=2, column=1,
                                                                                                  sticky=W, padx=10,
                                                                                                  pady=5)

        Entry(self.root, width=70, textvariable=self.v3, state='readonly').grid(row=3, column=0)
        Button(self.root, text='下载', width=10, command=lambda: self.load(self.v3.get())).grid(row=3, column=1,
                                                                                                  sticky=W, padx=10,
                                                                                                  pady=5)

        Entry(self.root, width=70, textvariable=self.v4, state='readonly').grid(row=4, column=0)
        Button(self.root, text='下载', width=10, command=lambda: self.load(self.v4.get())).grid(row=4, column=1,
                                                                                                  sticky=W, padx=10,
                                                                                                  pady=5)

        Entry(self.root, width=70, textvariable=self.v5, state='readonly').grid(row=5, column=0)
        Button(self.root, text='下载', width=10, command=lambda: self.load(self.v5.get())).grid(row=5, column=1,
                                                                                                  sticky=W, padx=10,
                                                                                                  pady=5)

        Entry(self.root, width=70, textvariable=self.v6, state='readonly').grid(row=6, column=0)
        Button(self.root, text='下载', width=10, command=lambda: self.load(self.v6.get())).grid(row=6, column=1,
                                                                                                  sticky=W, padx=10,
                                                                                                  pady=5)

        Entry(self.root, width=70, textvariable=self.v7, state='readonly').grid(row=7, column=0)
        Button(self.root, text='下载', width=10, command=lambda: self.load(self.v7.get())).grid(row=7, column=1,
                                                                                                  sticky=W, padx=10,
                                                                                                  pady=5)

        Entry(self.root, width=70, textvariable=self.v8, state='readonly').grid(row=8, column=0)
        Button(self.root, text='下载', width=10, command=lambda: self.load(self.v8.get())).grid(row=8, column=1,
                                                                                                  sticky=W, padx=10,
                                                                                                  pady=5)

        Entry(self.root, width=70, textvariable=self.v9, state='readonly').grid(row=9, column=0)
        Button(self.root, text='下载', width=10, command=lambda: self.load(self.v9.get())).grid(row=9, column=1,
                                                                                                  sticky=W, padx=10,
                                                                                                  pady=5)

        Button(self.root, text='上一页', width=10, command=self.former).grid(row=10, column=0,
                                                                              sticky=W, padx=10, pady=5)
        Button(self.root, text='下一页', width=10, command=self.latter).grid(row=10, column=0,
                                                                          sticky=E, padx=10, pady=5)
        Button(self.root, text='退出', width=10, command=self.root.quit).grid(row=10, column=1,
                                                                            sticky=E, padx=10, pady=5)

        self.init()
        # mainloop()

    def load(self, keyid):
        keyid = re.findall(r'av(.*?) ', keyid)[0]
        urlA = 'https://www.bilibili.com/video/av' + keyid
        # sys.argv = ['you-get', '-o', self.fileadd, urlA]
        # you_get.main()
        os.system('you-get -o ' + self.fileadd + ' ' + urlA)
        tkinter.messagebox.showinfo(message='下载完成...')

    def former(self):
        if self.page == 1 and self.part == 0:
            tkinter.messagebox.showinfo(message='没有上一页！')
            return
        if self.part == 1:
            self.part = 0
        else:
            self.part = 1
            self.page -= 1
            self.result = get_url(self.page, self.keyword)
        self.init()

    def latter(self):
        if self.part == 1:
            self.part = 0
            self.page += 1
            self.result = get_url(self.page, self.keyword)
        else:
            self.part = 1
        self.init()

    def init(self):
        k = self.part * 10

        Str = '{0:12}{1:8}{2:50}'.format(self.result[k+0][0], self.result[k+0][1], self.result[k+0][2])
        self.v0.set(Str)

        Str = '{0:12}{1:8}{2:50}'.format(self.result[k+1][0], self.result[k+1][1], self.result[k+1][2])
        self.v1.set(Str)

        Str = '{0:12}{1:8}{2:50}'.format(self.result[k+2][0], self.result[k+2][1], self.result[k+2][2])
        self.v2.set(Str)

        Str = '{0:12}{1:8}{2:50}'.format(self.result[k+3][0], self.result[k+3][1], self.result[k+3][2])
        self.v3.set(Str)

        Str = '{0:12}{1:8}{2:50}'.format(self.result[k+4][0], self.result[k+4][1], self.result[k+4][2])
        self.v4.set(Str)

        Str = '{0:12}{1:8}{2:50}'.format(self.result[k+5][0], self.result[k+5][1], self.result[k+5][2])
        self.v5.set(Str)

        Str = '{0:12}{1:8}{2:50}'.format(self.result[k+6][0], self.result[k+6][1], self.result[k+6][2])
        self.v6.set(Str)

        Str = '{0:12}{1:8}{2:50}'.format(self.result[k+7][0], self.result[k+7][1], self.result[k+7][2])
        self.v7.set(Str)

        Str = '{0:12}{1:8}{2:50}'.format(self.result[k+8][0], self.result[k+8][1], self.result[k+8][2])
        self.v8.set(Str)

        Str = '{0:12}{1:8}{2:50}'.format(self.result[k+9][0], self.result[k+9][1], self.result[k+9][2])
        self.v9.set(Str)

def load():
    if e1.get() == '' or e2.get() == '':
        return
    fileadd = e1.get()
    keyword = e2.get()

    root.destroy()

    Test = Tk_class(root_master, fileadd, keyword)
    Test.init()

if __name__ == '__main__':
    os.system('pip install you_get')
    root_master = Tk()

    root = tkinter.Frame(root_master)
    root.pack()
    Label(root, text='下载目录 :').grid(row=0, column=0)
    Label(root, text='关键字 :').grid(row=1, column=0)

    list = StringVar()
    url = StringVar()

    e1 = Entry(root, textvariable=list)
    e2 = Entry(root, textvariable=url)

    e1.grid(row=0, column=1, padx=10, pady=5)
    e2.grid(row=1, column=1, padx=10, pady=5)

    Button(root, text='搜索', width=10, command=load).grid(row=3, column=0, sticky=W, padx=10, pady=5)
    Button(root, text='退出', width=10, command=root_master.quit).grid(row=3, column=1, sticky=E, padx=10, pady=5)

    mainloop()