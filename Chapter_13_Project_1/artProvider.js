
const fs = require('fs');
const path = require('path');


const artData = JSON.parse(
  fs.readFileSync(path.join(__dirname, 'paintings.json'), 'utf8')
);


function getAllPaintings() {
  return artData;
}


function getPaintingById(id) {
  return artData.find(painting => painting.paintingID.toString() === id);
}


function getPaintingsByGalleryId(galleryId) {
  return artData.filter(painting => 
    painting.gallery && painting.gallery.galleryID.toString() === galleryId
  );
}


function getPaintingsByArtistId(artistId) {
  return artData.filter(painting => 
    painting.artist && painting.artist.artistID.toString() === artistId
  );
}


function getPaintingsByYearRange(min, max) {
  return artData.filter(
    painting => painting.yearOfWork >= min && painting.yearOfWork <= max
  );
}

module.exports = {
  getAllPaintings,
  getPaintingById,
  getPaintingsByGalleryId,
  getPaintingsByArtistId,
  getPaintingsByYearRange
};