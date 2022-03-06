new Vue({
    el: "#app",
    data: {
        category: "",
        head: "",
        goods: "",
        links: [],
        pageNum: 1,
        total: 1,
        name: "",
    },
    methods: {
        handleSelect(key, keyPath) {
            location.href = "toIndex";
            console.log(key, keyPath);
        },
        page() {
            let that = this;
            axios.get('goods/findPage?pageNum=' + this.pageNum + '&links=' + this.links)
                .then(function (response) {
                    that.goods = response.data.data;
                    that.total = response.data.page.total;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        choice(){
            this.pageNum = 1;
            let that = this;
            for (let index = 0; index < this.links.length; index++) {
                if(this.links[index]==0 || this.links[index]==null){
                    this.links[index]=0;
                }
            }
            axios.get('goods/findPage?pageNum=' + this.pageNum + '&links=' + this.links)
                .then(function (response) {
                    that.goods = response.data.data;
                    that.total = response.data.page.total;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        findByName(){
            this.pageNum = 1;
            let that = this;
            axios.get('goods/name?pageNum=' + this.pageNum + '&links=' + this.links + '&name=' + this.name)
                .then(function (response) {
                    that.goods = response.data.data;
                    that.total = response.data.page.total;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        tt(){
            alert("sad");
        }
    },
    created() {
        let reg = new RegExp("(^|&)cid=([^&]*)(&|$)", "i");
        let r = window.location.search.substr(1).match(reg);
        if (r != null) {
            if (r[2] == null || r[2] == '') {
                r[2] = 1;
            }
        } else {
            r = [1,1,1];
        }
        let that = this;
        function findLinkHead() {
            return axios.get('category/findLinkHead?cid=' +r[2]);
        }
        function findLink() {
            return axios.get('category/findLink?cid=' + r[2]);
        }
        function getFirst() {
            return axios.get('goods/first');
        }
        axios.all([findLinkHead(), findLink(), getFirst()])
            .then(axios.spread(function (head, link, first) {
                that.head = head.data.data;
                that.category = link.data.data;
                that.goods = first.data.data;
                that.total = first.data.page.total;
            }))
            .catch(function (error) {
                console.log(error);
            });
    },
})