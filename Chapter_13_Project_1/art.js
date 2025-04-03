
const express = require('express');
const path = require('path');
const artProvider = require('./artProvider');

const app = express();
const PORT = process.env.PORT || 3000;


app.use(express.static(path.join(__dirname, 'static')));


app.get('/', (req, res) => {
  res.json(artProvider.getAllPaintings());
});


app.get('/:id', (req, res) => {
  const painting = artProvider.getPaintingById(req.params.id);
  
  if (painting) {
    res.json(painting);
  } else {
    res.status(404).json({ error: 'Painting not found' });
  }
});


app.get('/gallery/:id', (req, res) => {
  const paintings = artProvider.getPaintingsByGalleryId(req.params.id);
  
  if (paintings.length > 0) {
    res.json(paintings);
  } else {
    res.status(404).json({ error: 'No paintings found for this gallery' });
  }
});


app.get('/artist/:id', (req, res) => {
  const paintings = artProvider.getPaintingsByArtistId(req.params.id);
  
  if (paintings.length > 0) {
    res.json(paintings);
  } else {
    res.status(404).json({ error: 'No paintings found for this artist' });
  }
});


app.get('/year/:min/:max', (req, res) => {
  const min = parseInt(req.params.min);
  const max = parseInt(req.params.max);
  
  if (isNaN(min) || isNaN(max)) {
    return res.status(400).json({ error: 'Invalid year range' });
  }
  
  const paintings = artProvider.getPaintingsByYearRange(min, max);
  
  if (paintings.length > 0) {
    res.json(paintings);
  } else {
    res.status(404).json({ error: 'No paintings found in this year range' });
  }
});


app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});