

module.exports = {
    devServer : {
        // client: {
        //     overlay : false
        // },
        proxy : {
            '/api' : {
                target : process.env.NODE_ENV === 'docker' ? "http://server:8080/" : "http://localhost:8080",
                changeOrigin : true
            }
        }
    },

    transpileDependencies: [
      'vuetify'
    ]
}
