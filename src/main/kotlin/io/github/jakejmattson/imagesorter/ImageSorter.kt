package io.github.jakejmattson.imagesorter

import java.awt.image.*
import java.io.File
import javax.imageio.ImageIO

fun main() {
	val input = File("input.png")
	val output = File("output.png")
	val image = ImageIO.read(input)!!

	val pixels = image.extractPixels().also { it.sort() }

	val newImage = BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_RGB)
	newImage.setRGB(0, 0, image.width, image.height, pixels, 0, image.width)

	ImageIO.write(newImage, output.extension, output)
}

private fun BufferedImage.extractPixels(): IntArray {
	val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
	image.graphics.drawImage(this, 0, 0, null)
	return (image.raster.dataBuffer as DataBufferInt).data
}