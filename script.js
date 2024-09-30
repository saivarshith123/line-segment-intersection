document.getElementById('lineForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const p1 = { x: parseInt(document.getElementById('p1x').value), y: parseInt(document.getElementById('p1y').value) };
    const q1 = { x: parseInt(document.getElementById('q1x').value), y: parseInt(document.getElementById('q1y').value) };
    const p2 = { x: parseInt(document.getElementById('p2x').value), y: parseInt(document.getElementById('p2y').value) };
    const q2 = { x: parseInt(document.getElementById('q2x').value), y: parseInt(document.getElementById('q2y').value) };

    const result = doIntersect(p1, q1, p2, q2) ? "Line segments are intersecting." : "Line segments are not intersecting.";
    document.getElementById('result').innerText = result;
});

function onSegment(p, q, r) {
    return q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
           q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y);
}

function orientation(p, q, r) {
    const val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
    if (val === 0) return 0;
    return (val > 0) ? 1 : 2;
}

function doIntersect(p1, q1, p2, q2) {
    const o1 = orientation(p1, q1, p2);
    const o2 = orientation(p1, q1, q2);
    const o3 = orientation(p2, q2, p1);
    const o4 = orientation(p2, q2, q1);

    if (o1 !== o2 && o3 !== o4) return true;
    if (o1 === 0 && onSegment(p1, p2, q1)) return true;
    if (o2 === 0 && onSegment(p1, q2, q1)) return true;
    if (o3 === 0 && onSegment(p2, p1, q2)) return true;
    if (o4 === 0 && onSegment(p2, q1, q2)) return true;

    return false;
}
