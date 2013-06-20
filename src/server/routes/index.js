require('../db');
var mongoose = require('mongoose');
var utils = require('connect').utils;

var Book = mongoose.model('Book');

/*
 * GET home page.
 */

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

exports.destroy = function(req,res,next){
  Book.findById(req.params.id, function(err, book){
    book.remove(function(err, todo){
      if (err) return next(err);
      res.redirect('/');
    });
  });
};

exports.create = function(req,res,next){
  new Book({
    user_id: req.cookies.user_id,
    title: req.body.title,
    date: Date.now() 
  }).save( function(err, book, count) {
    if (err) return next(err);
    res.redirect('/');
  });
};

exports.current_user = function(req,res,next){
  if (!req.cookies.user_id) {
    res.cookie('user_id', utils.uid(32));
  }

  next();
};
