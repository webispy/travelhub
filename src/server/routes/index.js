var utils = require('connect').utils;
var fs = require('fs');
var mysql = require('mysql');
var conn = mysql.createConnection({
	host : 'localhost',
	user : 'travel',
	password: 'travel',
	database: 'travel'
});


var dirBase = "public/data/";

var deleteFolderRecursive = function(path) {
	if( fs.existsSync(path) ) {
		fs.readdirSync(path).forEach(function(file,index){
			var curPath = path + "/" + file;
			if(fs.statSync(curPath).isDirectory()) {
				deleteFolderRecursive(curPath);
			}
			else {
				fs.unlinkSync(curPath);
			}
		});
		fs.rmdirSync(path);
	}
};




exports.db = function(req, res, next){
	conn.query('SHOW TABLES', function(err, rows) {
		console.log(err);
		console.log(rows);
		res.json(rows);
	});
};


exports.user_list = function(req, res, next){
	if (conn.state == 'disconnected') {
		console.log("no mysql connection");
		res.json({result: 'failure'});
		return;
	}

	conn.query('SELECT * FROM User', function(err, rows) {
		console.log(err);
		console.log(rows);
		res.json(rows);
	});
};

exports.user_login = function(req, res, next){
	var query = "SELECT * FROM User WHERE "
		+ "email = '" + req.body.email + "' AND "
		+ "password = '" + req.body.password + "'";

	console.log(query);

	if (conn.state == 'disconnected') {
		console.log("no mysql connection");
		res.json({result: 'failure'});
		return;
	}

	conn.query(query, function(err, rows) {
		if (err) {
			console.log(err);
			res.json({result: 'failure'});
		}
		else {
			console.log(rows);
			if (rows.length == 0)
				res.json({result: 'failure'});
			else
				res.json({result: 'success', data: rows[0]});
		}
	});
};

exports.user_add = function(req, res, next){
	var query = "INSERT INTO User (nickname, email, password) values ('"
		+ req.body.nickname + "','"
		+ req.body.email + "','"
		+ req.body.password + "')";

	console.log(query);

	if (conn.state == 'disconnected') {
		console.log("no mysql connection");
		res.json({result: 'failure'});
		return;
	}

	conn.query(query, function(err, rows) {
		if (err) {
			console.log(err);
			res.json({result: 'failure'});
		}
		else {
			console.log(rows);
			if (rows.length == 0)
				res.json({result: 'failure'});
			else
				res.json({result: 'success', data: rows});
		}
	});
};

exports.user_del = function(req, res, next){
	if (conn.state == 'disconnected') {
		console.log("no mysql connection");
		res.json({result: 'failure'});
		return;
	}

	if (req.params.id) {
		conn.query('DELETE FROM User WHERE id=' + req.params.id, function(err, rows) {
			if (err) {
				console.log(err);
				res.json({result: 'failure'});
			}
			else {
				console.log(rows);
				if (rows.length == 0)
				res.json({result: 'failure'});
				else
				res.json({result: 'success', data: rows});
			}
		});
	}
};

exports.user_find = function(req, res, next){
	if (conn.state == 'disconnected') {
		console.log("no mysql connection");
		res.json({result: 'failure'});
		return;
	}

	if (req.params.id) {
		conn.query('SELECT * FROM User WHERE email=' + req.params.email, function(err, rows) {
			console.log(rows);
			res.send(rows);
			res.redirect('back');
		});
	}
};

/* mongodb */


/*
 * GET home page.
 */
/*
exports.index = function(req, res, next){
  Book.
    find().
    sort('-date').
    exec(function(err, books, count) {
      if (err) return next(err);

      res.render('index', {
        title: 'TravelBook list',
        user_id: req.cookies.user_id,
        books: books,
        count: count
      });
    });
};

exports.list = function(req, res, next){
	Book.
	find().
	sort('-date').
	exec(function(err, books, count) {
		if (err) return next(err);

		console.log(books.length);
		for (i=0; i < books.length; i++) {
			d = new Date(books[i].date);
			books[i].date2 = d.getTime();
			books[i]['kk'] = "haha";
			console.log(books[i]);
		}

		console.log(books);

		res.json(books);
	});
};

exports.destroy = function(req,res,next){
	Book.findById(req.params.id, function(err, book){
		if (book) {
			var bid = book._id;
			book.remove(function(err, book){
				if (err) return next(err);

				deleteFolderRecursive(dirBase + bid);

				res.redirect('/');
			});
		}
		else {
			console.log("unknown id:" + req.params.id);
		}
	});
};

exports.create = function(req,res,next){
	new Book({
		//user_id: req.cookies.user_id,
		user_id: req.body.user_id,
		title: req.body.title,
		front_img: req.body.front_img,
		date: Date.now()
		}).save( function(err, book, count) {
			if (err) return next(err);

			fs.mkdir (dirBase + book._id, function(e){
				console.log(e);
			});

			console.log(book);

			fs.writeFileSync(dirBase + book._id + '/front.png', fs.readFileSync('public/images/' + book.front_img));

			res.redirect('/');
		});
	};

exports.current_user = function(req,res,next){
  if (!req.cookies.user_id) {
    res.cookie('user_id', utils.uid(32));
  }

  next();
};
*/
