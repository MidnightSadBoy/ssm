new Vue({
	el: "#app",
	data: {
		activeIndex: '1',
		activeIndex2: '1',
		category: "",
		goodscal: "",
		goodsindex: "",
		username: "",
		isLogin: "false",
	},
	methods: {
		handleSelect(key, keyPath) {
			if(!this.isLogin && key==2){
				location.href = "toLogin";
			}
			if(!this.isLogin && key==3){
				location.href = "toRegister";
			}
			console.log(key, keyPath);
		},
		choice(key, keyPath){
			location.href = "toGoods_list?cid="+key;
			console.log(key,keyPath);
		},
		open1() {
			this.$message({
				message: '恭喜你，这是一条成功消息',
				type: 'success'
			});
		},
		open2() {
			this.$message.error('错了哦，这是一条错误消息');
		},
	}, created() {
		var that = this;

		function getFindAll() {
			return axios.get('category/findAll');
		}

		function getCal() {
			return axios.get('goods/cal');
		}

		function getIndex() {
			return axios.get('goods/index');
		}

		function findUsername() {
			return axios.get('user/findUsername');
		}

		axios.all([getFindAll(), getCal(), getIndex(), findUsername()])
			.then(axios.spread(function (all, cal, index, username) {
				that.category = all.data.data;
				that.goodscal = cal.data.data;
				that.goodsindex = index.data.data;
				that.username = username.data.data;
				that.isLogin = username.data.flag;
			}))
			.catch(function (error) {
				console.log(error);
			});
	},
})