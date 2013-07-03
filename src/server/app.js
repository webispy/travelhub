var express = require('express')
  , routes = require('./routes')
  , http = require('http')
  , path = require('path');

var app = express();


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
//	app.use(routes.current_user);
	app.use(app.router);
});

// development only
app.configure('development', function() {
  app.use(express.errorHandler({ dumpExceptions: true, showStack: true }));
});

app.configure('production', function() {
  app.use(express.errorHandler());
});

//app.get('/', routes.index);
//app.get('/list?:callback', routes.list);
//app.post('/create', routes.create);
//app.get('/users', user.list);
//app.get('/destroy/:id', routes.destroy);

app.get('/db', routes.db);

app.post('/user/add', routes.user_add);
app.post('/user/login', routes.user_login);
app.get('/user/list', routes.user_list);
app.get('/user/del/:id', routes.user_del);
app.get('/user/find/:email', routes.user_find);

http.createServer(app).listen(app.get('port'), function(){
  console.log('Express server listening on port %d in %s mode', app.get('port'), app.settings.env );
});
