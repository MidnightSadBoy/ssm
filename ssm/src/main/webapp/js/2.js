new Vue({
    el: "#app",
    data: {
    },
    methods: {
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        handleSelect(key, keyPath){
            alert();
            console.log(key, keyPath);
        },
        choice(key, keyPath){
            console.log(key, keyPath);
        }
    }
})