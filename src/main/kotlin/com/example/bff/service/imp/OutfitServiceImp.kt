package com.example.bff.service.imp

import com.example.bff.builder.OutfitScreen
import com.example.bff.service.OutfitService
import org.springframework.stereotype.Service

@Service
class OutfitServiceImp : OutfitService {
    override fun getOutfitScreen(): OutfitScreen = OutfitScreen()
}