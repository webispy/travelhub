
/**
 * Module dependencies.
 */

var express = require('express')
  , routes = require('./routes')
  , user = require('./routes/user')
  , http = require('http')
  , path = require('path');

var app = express();

require('./db');

// all environments
app.configure(function() {
	app.set('port', process.env.PORT || 8000);
	app.set('views', __dirname + '/views');
	app.set('view engine', 'ejs');
	app.use(express.favicon());
	app.use(express.static(path.join(__dirname, 'public')));
	app.use(express.logger('dev'));
	app.use(express.cookieParser('your secret here'));
	app.use(express.bodyParser());
	app.use(express.methodOverride());
	app.use(express.session());
	app.use(routes.current_user);
	app.use(app.router);
});

// development only
app.configure('development', function() {
  app.use(express.errorHandler({ dumpExceptions: true, showStack: true }));
});

app.configure('production', function() {
  app.use(express.errorHandler());
});

app.get('/', routes.index);
app.post('/create', routes.create);
app.get('/users', user.list);
app.get('/destroy/:id', routes.destroy);

http.createServer(app).listen(app.get('port'), function(){
  console.log('Express server listening on port %d in %s mode', app.get('port'), app.settings.env );
});
