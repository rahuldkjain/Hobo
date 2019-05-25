module.exports = {
    devServer: {
        proxy: {
            '/api': {
                target: 'http://172.16.20.84:8080/',
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            },
            '/allProducts': {
                target: 'http://172.16.20.80:8080/',
                changeOrigin: true,
                pathRewrite: {
                    '^/allProducts': ''
                }
            },
            '/goToProduct': {
                target: 'http://172.16.20.80:8080/',
                changeOrigin: true,
                pathRewrite: {
                    '^/goToProduct': ''
                }
            },
            '/merchantProduct': {
                target: 'http://172.16.20.101:8080/',
                changeOrigin: true,
                pathRewrite: {
                    '^/merchantProduct': ''
                }
            },
            '/fetchCartProduct': {
                target: 'http://172.16.20.80:8080/',
                changeOrigin: true,
                pathRewrite: {
                    '^/fetchCartProduct': ''
                }
            },
            '/user': {
                target: 'http://172.16.20.84:8081/',
                changeOrigin: true,
                pathRewrite: {
                    '^/user': ''
                }
            },
            '/merchant': {
                target: 'http://172.16.20.84:8081/',
                changeOrigin: true,
                pathRewrite: {
                    '^/merchant': ''
                }
            }
        }
    }
}