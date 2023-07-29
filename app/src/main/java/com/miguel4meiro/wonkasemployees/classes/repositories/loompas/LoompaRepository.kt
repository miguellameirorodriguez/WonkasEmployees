package com.miguel4meiro.wonkasemployees.classes.repositories.loompas

import com.miguel4meiro.wonkasemployees.classes.models.app.GenderEnum
import com.miguel4meiro.wonkasemployees.classes.models.app.Loompa
import com.miguel4meiro.wonkasemployees.classes.models.app.LoompasResponse
import com.miguel4meiro.wonkasemployees.classes.models.app.ProfessionEnum
import com.miguel4meiro.wonkasemployees.classes.models.app.getValue
import com.miguel4meiro.wonkasemployees.classes.services.api.loompas.LoompaServiceInterface
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class LoompaRepository(
    private val loompaService: LoompaServiceInterface
): LoompaRepositoryInterface {

    override suspend fun getLoompas(page:Int, genderFilter: GenderEnum?, professionFilter: ProfessionEnum?): LoompasResponse {
        return withContext(IO) {
            val result = loompaService.getLoompas(page)

            val filteredList = result.loompasDto?.filter { loompa ->
                (genderFilter == null || loompa.gender == genderFilter.getValue()) &&
                        (professionFilter == null || loompa.profession == professionFilter.getValue())
            }?.toList()


            return@withContext result.copy(loompasDto = filteredList).toModel()
        }
    }

    override suspend fun getLoompa(id: Int): Loompa {
        return withContext(IO) {
            val result = loompaService.getLoompa(id)

            return@withContext result.toModel()
        }
    }
}