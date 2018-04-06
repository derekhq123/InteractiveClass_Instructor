ESCInstructor
SC - Instructor App Android application for smart interactive classroom to help professors with teaching and students with learning.

Features that have been implemented:

Login Screen
Checks against instructor list from firebase database to verify instructor name and password are correct before allowing entry; After that, the instructor would be able to enter a certain class by clicking the button in a tablelayout;

Home Screen
With navigation bar at the bottom.Instructors can view quiz scores of all students or fail list by clicking corresbonding buttons;

Forum
Read questions from Forum.The question data is from firebase, uploaded by students; Basic click on recycler view to jump to reply screen for viewing the full question and writing/changing reply to the corresponding question;

Student list
Displays the score for every quiz, overall score and grade for all the student in the present classroom,sorted by student ID; The fail list displays the student ids with an F grade.

Quiz
Allows instructor to start editting a new quiz and post it to all students in the present classroom Instructors are able to choose from two different question types (MCQ and short answer question);

Broadcast Question
Allows instructor to post multiple choice questions to class, with the correct answer and three other options;

Features that remain to be integrated:
List of students answered broadcast questions;
Feedback display screen for lectures and instructor;
User preference screen.