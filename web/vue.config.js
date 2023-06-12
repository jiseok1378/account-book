

module.exports = {
    publicPath: '/',
    devServer : {
        allowedHosts: "all",
        client: {
            overlay : false
        },
        proxy : {
            '/api' : {
                target : process.env.NODE_ENV === 'docker' ? "http://app-server:8080/" : "http://localhost:8080",
                changeOrigin : true
            },
            '/upload' : {
                target : process.env.NODE_ENV === 'docker' ? "http://app-server:8080/" : "http://localhost:8080",
                changeOrigin : true
            }
        }
    },

    transpileDependencies: [
      'vuetify'
    ]
}
