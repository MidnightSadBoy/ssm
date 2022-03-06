new Vue({
    el: '#app',
    data: {
        h: "",
        username: "",
        password: "",
        checkcode: "",
        email: "",
        telephone: "",
        birthday: "",
        sex: "m",
        us: "",
        pa: "",
        em: "",
        te: "",
        msg: "",
        bi: "",
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
        // 表单提交事件
        loginSubmit() {
            let that = this;
            var params = new URLSearchParams();
            params.append("username", this.username);
            params.append("password", this.password);
            params.append("email", this.email);
            params.append("telephone", this.telephone);
            params.append("birthday", this.birthday);
            params.append("sex", this.sex);
            params.append("checkcode", this.checkcode);
            if (this.checkUsername() && this.checkPassword() && this.checkEmail() &&  this.checkTel() && this.checkDate()) {
                axios.post('user/register', params)
                    .then(function (response) {
                        if (response.data.flag) {
                            location.href = "toRegisterSuccess";
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
        checkEmail() {
            let reg_mail = /^([a-zA-Z0-9]+[_|_|\-|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,6}$/;
            let flag = reg_mail.test(this.email);
            if (flag) {
                this.em = "√";
            } else {
                this.em = "请输入正确的邮箱格式";
            }
            return flag;
        },
        checkTel() {
            let reg_tel = /^\d{8,14}$/;
            let flag = reg_tel.test(this.telephone);
            if (flag) {
                this.te = "√";
            } else {
                this.te = "只能输入8到14位数字";
            }
            return flag;
        },
        checkDate() {
            if (this.birthday== "" || this.birthday == null) {
                this.bi = "请输入日期";
                return false;
            } else {
                this.bi = "√";
                return true;
            }
        },
        refreshCode() {
            this.imgcode = "user/checkCode?time=" + new Date().getTime();
        },
    },
    created() {
        //获取屏幕高度
        this.h = (document.documentElement.clientHeight || document.body.clientHeight) - 61;
    },
})