I change the rule a little bit:
Level 1,2,3: you only need to type correctly ONE word to pass to next level.

For loading words:
For level 1,2 I load the words from file twoWord and threeWord (.txt)
For level 3,4,5... I load the words from API: https://random-word-api.herokuapp.com/word
	with query param "number = 200" => load 200 random words.  
	Filter in those words, If we cannot take enough words (with appropriate number of characters), the API will be called again but with 
	query param "number = 200 * (times we called api +1)" for example, we called API 3 times but cannot take enough words we will call api again with
	"number = 200 * (3+1)" => "number = 800"
	
Thank you for reading.
