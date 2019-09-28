package ro.utcn.sd.vasi.SnackOverflow.data_assembler;


import ro.utcn.sd.vasi.SnackOverflow.model.VoteAnswer;
import ro.utcn.sd.vasi.SnackOverflow.model.VoteQuestion;

import java.util.List;

public class ScoreCalcuator {

    public static int computeUserScore(List<VoteQuestion> receivedQuestion, List<VoteAnswer> receivedAnswer, List<VoteQuestion> givenQuestion, List<VoteAnswer> givenAnsser) {
        int nrReceivedUpvotedQuestions = 0, nrReceivedDownvotedQuestions = 0;
        int nrReceivedUpvotedAnswers = 0, nrReceivedDownvotedAnswers = 0;
        int nrGivedDownvotedPosts = 0;

        nrReceivedUpvotedQuestions = (int) receivedQuestion.stream().filter(x -> x.isValue()).count();
        nrReceivedDownvotedQuestions = receivedQuestion.size() - nrReceivedUpvotedQuestions;

        nrReceivedUpvotedAnswers = (int) receivedAnswer.stream().filter(x -> x.isValue()).count();
        nrReceivedDownvotedAnswers = receivedAnswer.size() - nrReceivedUpvotedAnswers;

        nrGivedDownvotedPosts = (int) givenAnsser.stream().filter(x -> !x.isValue()).count();
        nrGivedDownvotedPosts += (int) givenQuestion.stream().filter(x -> !x.isValue()).count();

        return nrReceivedUpvotedQuestions * 5 + nrReceivedUpvotedAnswers * 10
                - nrReceivedDownvotedAnswers * 2 - nrReceivedDownvotedQuestions * 2 - nrGivedDownvotedPosts * 1;
    }

    public static int computeAnswerScore(List<VoteAnswer> receivedVotes) {
        int upvotes = (int) receivedVotes.stream().filter(x -> x.isValue()).count();

        return 2 * upvotes - receivedVotes.size();
    }

    public static int computeQuestionScore(List<VoteQuestion> receivedVotes) {
        int upvotes = (int) receivedVotes.stream().filter(x -> x.isValue()).count();

        return 2 * upvotes - receivedVotes.size();
    }
}

