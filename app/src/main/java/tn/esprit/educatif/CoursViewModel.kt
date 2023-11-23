package tn.esprit.educatif

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import tn.esprit.educatif.model.Cours

class CoursViewModel(private val repository: CoursRepository) : ViewModel() {
    fun getAllCours(): LiveData<List<Cours>> {
        return repository.getAllCours()
    }
    // LiveData for adding a new course
    fun addCours(cours: Cours): LiveData<Boolean> {
        return repository.addCours(cours)
    }

    // LiveData for updating an existing course
    fun updateCours(coursId: String, updatedCours: Cours): LiveData<Boolean> {
        return repository.updateCours(coursId, updatedCours)
    }

    // LiveData for deleting a course
    fun deleteCours(coursId: String): LiveData<Boolean> {
        return repository.deleteCours(coursId)
    }
}