var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var BookSchema = new Schema({
	user_id : String,
	title : String,
	date : Date,
	key : String
});

mongoose.connect('mongodb://localhost/travelhub');
mongoose.model('Book', BookSchema);
