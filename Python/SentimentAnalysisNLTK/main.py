from template import sentiment_analysis_result
from template import trainmodel

test_phrases = {
    "#inaperfectworld final exams wouldn't exist and i would be sleeping very confy right now!": 1,
    "ok thats it you win.": 0,
    "#Swim&amp;Run #Cologne was great!Lesson learnedon't bring water w/ gas.Cost me 5-7min.,easy ": 1,
    "#Tetris, one of the most renowned and addictive creations in the brief history of video games, turns 25 this week  Long live Tetris!": 1,
    "#TFARP -cycle. Been a rather long solar cycle for everybody, I think. :p G'night Bots, Cons, and assorted others! ": 0,
    "#thankyouGod for all the mothers in the world who doesnt know how to cook salsa but will be willing to try for their children ": 1,
    "#inaperfectworld i'd be sitting VIP row 1 at the game tonight!": 1,
    "#iPhone - updated to 3.0 but cant activate because  iTunes store activation connection time out error ": 0,
    "#Up is amazing!  I'm glad I got to see it &amp; see @KristinRose84 too!": 1,
    "#Music happy music monday everybody, I LOVE MUSIC ": 1
}	

secret_test_phrases = {
}


if __name__ == "__main__":
    
    classifier =  trainmodel()
    nbr_test_phrases = len(test_phrases)
    nbr_secret_test_phrases = len(secret_test_phrases)

    count_correct = 0
    for phrase, score in test_phrases.items():
        res = sentiment_analysis_result(phrase,classifier)
        if res == score:
            count_correct += 1

    test_phases_count_correct = count_correct
    test_phrases_result = test_phases_count_correct/nbr_test_phrases * 100
    print('You have a score of {}% on the test phrases'.format(test_phrases_result))
    
    if test_phrases_result < 70:
    	print("You do not get a good score on the test phrases! {}".format("*" * 30))

    count_correct = 0
    for phrase, score in secret_test_phrases.items():
        res = sentiment_analysis_result(phrase)
        if res == score:
            count_correct += 1

    secret_test_phases_count_correct = count_correct
    secret_test_phrases_result = 0.0
    if nbr_secret_test_phrases != 0:
    	secret_test_phrases_result = secret_test_phases_count_correct/nbr_secret_test_phrases * 100
    print('You have a score of {}% on the secret test phrases'.format(secret_test_phrases_result))
    
    total_test_phrases_result = ((test_phases_count_correct + secret_test_phases_count_correct)/ \
    	(nbr_test_phrases + nbr_secret_test_phrases)) * 100

    print('You have a total score of {}%'.format(total_test_phrases_result))    