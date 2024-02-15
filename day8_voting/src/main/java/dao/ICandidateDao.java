package dao;

import java.sql.SQLException;
import java.util.List;

import pojos.Candidate;

public interface ICandidateDao {
//add a method to ret list of all candidates 
	List<Candidate> getAllCandidates() throws SQLException;

	// add a method to incr votes for a specific candidate
	String incrementCandidateVotes(int candidateId) throws SQLException;

}
