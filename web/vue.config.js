

module.exports = {
    devServer : {
        // client: {
        //     overlay : false
        // },
        proxy : {
            '/api' : {
                target : "http://localhost:8080",
                changeOrigin : true
            }
        }
    },
}