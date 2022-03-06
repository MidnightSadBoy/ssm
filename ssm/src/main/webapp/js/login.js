new Vue({
    el: '#app',
    data: {
        h: "",
        username: "",
        password: "",
        checkcode: "",
        us: "",
        pa: "",
        msg: "",
        imgcode: "user/checkCode",
    },
    methods: {
        //导航栏点击事件
        handleSelect(key, keyPath) {
            location.href = "toIndex";
            console.log(key, keyPath);
        },
        open1() {
            this.$message({
                message: '恭喜你，这是一条成功消息',
                type: 'success'
            });
        },
        open2() {
            this.$message.error(this.msg);
        },
        open3() {
            this.$alert('您已经登录请不要重复登录！', '登录通知', {
                confirmButtonText: '确定',
                callback: action => {
                    location.href = "toIndex";
                }
            });
        },
        // 表单提交事件
        loginSubmit() {
            let that = this;
            var params = new URLSearchParams();
            params.append("username", this.username);
            params.append("password", this.password);
            params.append("checkcode", this.checkcode);
            if (this.checkUsername() && this.checkPassword()) {
                axios.post('user/login', params)
                    .then(function (response) {
                        if (response.data.flag) {
                            location.href = "toIndex";
                        } else {
                            that.msg = response.data.errorMsg;
                            that.open2();
                        }
                        that.refreshCode();
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        },
        checkUsername() {
            //定义正则表达式
            let reg_username = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,12}$/;
            //判断是否符合正则表达式
            let flag = reg_username.test(this.username);
            //4.提示信息
            if (flag) {
                this.us = "√";//正确提示绿色对勾
            } else {
                this.us = "请输入长度为8到12位字符且必须带有字母、数字";//错误提示
            }
            return flag;
        },
        checkPassword() {
            //定义正则表达式
            let reg_password = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,12}$/;
            //判断是否符合正则表达式
            let flag = reg_password.test(this.password);
            //提示信息
            if (flag) {
                this.ps = "√";//正确提示绿色对勾
            } else {
                this.ps = "请输入长度为8到12位字符且必须带有字母、数字";//错误提示
            }
            return flag;
        },
        refreshCode() {
            this.imgcode = "user/checkCode?time=" + new Date().getTime();
        },
    },
    created() {
        //获取屏幕高度
        this.h = (document.documentElement.clientHeight || document.body.clientHeight) - 61;
        var that = this;

        function findUsername() {
            return axios.get('user/findUsername');
        }

        axios.all([findUsername()])
            .then(axios.spread(function (username) {
                if (username.data.flag) {
                    that.open3();
                }
            }))
            .catch(function (error) {
                console.log(error);
            });
    },
})