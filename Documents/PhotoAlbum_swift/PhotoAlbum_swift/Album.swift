//
//  Album.swift
//  PhotoAlbum_swift
//
//  Created by Conrado on 2014/12/26.
//  Copyright (c) 2014年 Conrado. All rights reserved.
//

import Foundation

func ==(lhs:Album, rhs:Album)->Bool{
    //should we use the album name or the album hashvalue?
    return lhs.albumName == rhs.albumName
}
class Album :Hashable{
    //The Model
    var albumName: String!
    
    var password: String!
    
    init(AlbumName: String){
        albumName = AlbumName
        // photos = [Photo]()
    }
    
    init(AlbumName: String, pass:String){
        albumName = AlbumName
        password = pass
    }
    
    func getAlbumName() ->String{
        return albumName
    }
    
    
    var hashValue : Int{
        get {
            return albumName.hashValue
        }
    }
    func getNumOfPhotos() ->Int{
        //for now only
        return 0
    }
    
    
}