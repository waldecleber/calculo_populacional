const proxy = [
    {
      context: ['/api'],
      target: 'http://localhost:7002',
      pathRewrite: {'^/api' : ''}
    }
  ];
  module.exports = proxy;